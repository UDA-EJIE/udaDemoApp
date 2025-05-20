package com.ejie.x21a.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ejie.x21a.model.Alumno;
import com.ejie.x21a.model.AlumnoDepartamento;
import com.ejie.x21a.model.Buzones;
import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Departamento;
import com.ejie.x21a.model.DepartamentoProvincia;
import com.ejie.x21a.model.DivisionTerritorialDto;
import com.ejie.x21a.model.FormComarcas;
import com.ejie.x21a.model.IberdokFile;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.MultiPk;
import com.ejie.x21a.model.NoraAutonomia;
import com.ejie.x21a.model.NoraCalle;
import com.ejie.x21a.model.NoraMunicipio;
import com.ejie.x21a.model.NoraPais;
import com.ejie.x21a.model.NoraProvincia;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.model.RandomForm;
import com.ejie.x21a.model.Usuario;
import com.ejie.x38.control.method.annotation.RequestJsonBodyMethodArgumentResolver;
import com.ejie.x38.log.model.LogModel;
import com.ejie.x38.serialization.CustomSerializer;
import com.ejie.x38.serialization.UdaMappingJackson2HttpMessageConverter;
import com.ejie.x38.serialization.UdaModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Configuración de Jackson.
 */
@ComponentScan("com.ejie.x21a")
@Configuration
@EnableWebMvc
public class JacksonConfig implements WebMvcConfigurer {

	/**
	 * Serializador utilizado por UDA para serializar unicamente las entidades
	 * deseadas.
	 * 
	 * @return Devuelve el serializador.
	 */
	@Bean
	public CustomSerializer customSerializer() {
		return new CustomSerializer();
	}

	/**
	 * HttpMessageConverter de UDA.
	 * 
	 * @return Devuelve el HttpMessageConverter.
	 */
	@Bean
	public UdaMappingJackson2HttpMessageConverter udaMappingJackson2HttpMessageConverter() {
		UdaMappingJackson2HttpMessageConverter httpMessageConverter = new UdaMappingJackson2HttpMessageConverter();

		List<MediaType> supportedMediaTypes = new ArrayList<>(httpMessageConverter.getSupportedMediaTypes());
		supportedMediaTypes.add(MediaType.valueOf(MediaType.TEXT_PLAIN_VALUE));
		supportedMediaTypes.add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

		httpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		httpMessageConverter.setUdaModule(udaModule());

		return httpMessageConverter;
	}

	/**
	 * Modulo de UDA para Jackson.
	 * 
	 * @return Devuelve el módulo.
	 */
	@Bean
	public UdaModule udaModule() {
		UdaModule udaModule = new UdaModule();

		Map<Class<? extends Object>, JsonSerializer<Object>> serializers = new HashMap<Class<? extends Object>, JsonSerializer<Object>>();
		serializers.put(Alumno.class, customSerializer());
		serializers.put(AlumnoDepartamento.class, customSerializer());
		serializers.put(FormComarcas.class, customSerializer());
		serializers.put(RandomForm.class, customSerializer());
		serializers.put(Comarca.class, customSerializer());
		serializers.put(Departamento.class, customSerializer());
		serializers.put(DepartamentoProvincia.class, customSerializer());
		serializers.put(Localidad.class, customSerializer());
		serializers.put(Provincia.class, customSerializer());
		serializers.put(Usuario.class, customSerializer());
		serializers.put(NoraPais.class, customSerializer());
		serializers.put(NoraAutonomia.class, customSerializer());
		serializers.put(NoraProvincia.class, customSerializer());
		serializers.put(NoraMunicipio.class, customSerializer());
		serializers.put(NoraCalle.class, customSerializer());
		serializers.put(MultiPk.class, customSerializer());
		serializers.put(Buzones.class, customSerializer());
		serializers.put(IberdokFile.class, customSerializer());
		serializers.put(LogModel.class, customSerializer());
		serializers.put(DivisionTerritorialDto.class, customSerializer());

		udaModule.setSerializers(serializers);
		//udaModule.setSerializationInclusions(serializationInclusions());
		udaModule.setSerializationFeature(serializationFeature());
		udaModule.setDeserializationFeature(deserializationFeature());

		return udaModule;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(udaMappingJackson2HttpMessageConverter());
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new RequestJsonBodyMethodArgumentResolver());
	}

	/**
	 * Inclusiones de serializacion.
	 * 
	 * @return Inclusiones a usar.
	 */
	private List<JsonInclude.Include> serializationInclusions() {
		return Arrays.asList(JsonInclude.Include.NON_NULL);
	}

	/**
	 * Features de configuracion de la serialización.
	 * 
	 * @return Features de configuración.
	 */
	private Map<SerializationFeature, Boolean> serializationFeature() {
		return new HashMap<SerializationFeature, Boolean>() {
			{
				put(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			}
		};
	}

	/**
	 * Features de configuracion de la deserializacion.
	 * 
	 * @return Features de configuración.
	 */
	private Map<DeserializationFeature, Boolean> deserializationFeature() {
		return new HashMap<DeserializationFeature, Boolean>() {
			{
				put(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			}
		};
	}

}
