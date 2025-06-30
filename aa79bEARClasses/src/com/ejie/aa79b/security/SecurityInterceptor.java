package com.ejie.aa79b.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.PropertiesConstants;
import com.ejie.aa79b.model.PersonalIZO;
import com.ejie.aa79b.model.Propiedad;
import com.ejie.aa79b.model.enums.BloqueoEnum;
import com.ejie.aa79b.service.PersonalIZOService;
import com.ejie.aa79b.service.PropiedadService;
import com.ejie.x38.security.MyAuthenticatedUserDetailsService;
import com.ejie.x38.security.MyUserDetails;
import com.ejie.x38.security.XlnetGrantedAuthority;

/**
 * @author dlopez.
 *
 */
public class SecurityInterceptor extends FilterSecurityInterceptor {

	@Autowired()
	private PersonalIZOService personalIZOService;
	@Autowired()
	private PropiedadService propiedadService;

	private static final String AA79B_LOOKED_PROFILE = "AA79B_LOOKED_PROFILE";

	@Override()
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpSession session = httpRequest.getSession(false);

		if (authentication != null) {
			final Usuario credentials = (Usuario) authentication.getCredentials();
			this.comprobarBloqueo(credentials, session);
			if (credentials.getUserProfiles() == null || !credentials.getUserProfiles().contains(AA79B_LOOKED_PROFILE)) {
				this.actualizarPermisosUsuario(request);
			}
		}
		super.doFilter(request, response, chain);
	}
	
	private void comprobarBloqueo(Usuario credentials, HttpSession session) {
		Boolean appBlocked = credentials.getBloqueado();
		if (!Boolean.FALSE.equals(appBlocked)) {
			final Propiedad bean = new Propiedad();
			bean.setId(PropertiesConstants.BLOQUEO_APLICACION);
			String valorBloqueo = this.propiedadService.find(bean).getValor();
			appBlocked = !BloqueoEnum.ABIERTO.getValue().equals(valorBloqueo);
			credentials.setBloqueado(appBlocked);
			if (appBlocked) {
				credentials.getUserProfiles().clear();
			}
		}
	}

	public void actualizarPermisosUsuario(ServletRequest request) {
		// ==============================================================================
		// NOTA DE SEGURIDAD
		// ==============================================================================
		// Este bloque esta para aÃ±adir los permisos que tiene el usuario en
		// X54J
		// ==============================================================================

		// Obtenemos la autenticacion del contexto de seguridad
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtenemos la credenciales de la autenticacion
		final Usuario credentials = (Usuario) authentication.getCredentials();

		// Obtenemos el detalle de usuario de la autenticacion
		final MyAuthenticatedUserDetailsService myAuthenticatedUserDetailsService = new MyAuthenticatedUserDetailsService();
		final UserDetails userDetails = myAuthenticatedUserDetailsService.loadUserDetails(authentication);

		// Obtenemos las autorizaciones del detalle de usuario
		final Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

		// Como no podemos trabajar sobre la lista de autorizaciones nos creamos
		// una nueva y aÃ±adimos las que habia
		final List<GrantedAuthority> newGrantedAuthorityList = new ArrayList<GrantedAuthority>();
		newGrantedAuthorityList.addAll(authorities);

		final List<String> profiles = credentials.getUserProfiles();

		if (!profiles.contains(AA79B_LOOKED_PROFILE)) {
			if (!credentials.getBloqueado()) {
				PersonalIZO personalIZO = new PersonalIZO();
				personalIZO.setDni(credentials.getNif());
				personalIZO = this.personalIZOService.find(personalIZO);
				if (personalIZO != null) {
					// AÃ±adimos los datos del solaskide a las credenciales
					credentials.getUserProfiles().add(AA79B_LOOKED_PROFILE);
					credentials.setIpPuesto(request.getRemoteAddr());
					credentials.setfullName(personalIZO.getNombreCompleto());
					credentials.setName(personalIZO.getNombre());
					credentials.setSurname(personalIZO.getApellido1() + " " + personalIZO.getApellido2());
	
					this.addRoles(personalIZO.getTecnicoGestor(), "TECNICO_GESTOR", credentials, newGrantedAuthorityList);
					this.addRoles(personalIZO.getTraductor(), "TRADUCTOR", credentials, newGrantedAuthorityList);
					this.addRoles(personalIZO.getInterprete(), "INTERPRETE", credentials, newGrantedAuthorityList);
					this.addRoles(personalIZO.getAsignador(), "ASIGNADOR", credentials, newGrantedAuthorityList);
					this.addRoles(personalIZO.getGestorBopv(), "GESTORBOPV", credentials, newGrantedAuthorityList);
				}
			}

			this.crearAutentificacionUsuario(authentication, newGrantedAuthorityList, userDetails);

		}

		// ==============================================================================
		// FIN DE NOTA DE SEGURIDAD
		// ==============================================================================
	}

	protected void addRoles(String valor, String permiso, Usuario credentials,
			List<GrantedAuthority> newGrantedAuthorityList) {
		if (Constants.SI.equals(valor)) {
			// AÃ±adimos el perfil que queremos a las credenciales
			credentials.getUserProfiles().add("AA79B_" + permiso);

			// Simulamos un rol de XLNets
			// Creamos una autorizacion aÃ±adiendo el prefijo "ROLE_" al perfil
			// y lo aÃ±adimos a la nueva lista de autorizaciones
			newGrantedAuthorityList.add(new XlnetGrantedAuthority("ROLE_AA79B_" + permiso));
		}
	}

	private void crearAutentificacionUsuario(Authentication authentication,
			List<GrantedAuthority> newGrantedAuthorityList, UserDetails userDetails) {
		// Creamos un nuevo detalle de usuario
		GrantedAuthority[] newGrantedAuthorityArray = new GrantedAuthority[newGrantedAuthorityList.size()];
		newGrantedAuthorityArray = newGrantedAuthorityList.toArray(newGrantedAuthorityArray);
		final UserDetails newUserDetails = new MyUserDetails(
				// UserName
				userDetails.getUsername(),
				// Password
				"N/A",
				// Enabled
				true,
				// AccountNonExpired
				true,
				// CredentialsNonExpired
				true,
				// Account Not Locked
				true, newGrantedAuthorityArray);

		// Regeneramos la autenticacion y la fijamos en el contexto de
		// seguridad
		PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken = new PreAuthenticatedAuthenticationToken(
				authentication.getPrincipal(), authentication.getCredentials(), newUserDetails.getAuthorities());
		preAuthenticatedAuthenticationToken.setDetails(authentication.getDetails());
		SecurityContextHolder.getContext().setAuthentication(preAuthenticatedAuthenticationToken);

	}

}
