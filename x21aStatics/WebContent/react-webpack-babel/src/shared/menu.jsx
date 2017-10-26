
import React from 'react';
import '../../styles/shared/menu.scss';

export default class Menu extends React.Component {
  render() {
    return (
      <nav className="rup-navbar navbar">
        <button type="button" className="navbar-toggler hidden-lg-up" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"></button>
        <div id="navbarResponsive" className="collapse navbar-toggleable-md">
          <a className="navbar-brand" href="#">Uda</a>
          <ul className="nav navbar-nav">
            <li className="nav-item dropdown">

              <a className="nav-link dropdown-toggle" href="#" id="responsiveNavbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Componentes</a>
              <div className="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
                  <a className="dropdown-item" href="/x21aAppWar/patrones/feedback"><i className="fa fa-check-square" aria-hidden="true"></i>Feedback</a>
                  <a className="dropdown-item" href="/x21aAppWar/patrones/tooltip"><i className="fa fa-comment-o" aria-hidden="true"></i>Tooltip</a>


              </div>
            </li>
          </ul>
          <ul className="nav navbar-nav float-md-right rup-nav-tools">
            <li className="nav-item">
              <a className="nav-link rup-nav-tool-icon" href="#" id="x21aApp_language" data-toggle="dropdown"><i className="fa fa-globe" aria-hidden="true"></i><span data-rup-lang-current=""></span></a>
      		<div className="dropdown-menu" aria-labelledby="x21aApp_language">
              </div>
            </li>
            <li className="nav-item">
              <a className="nav-link rup-nav-tool-icon" href="#"><i className="fa fa-cog " aria-hidden="true"></i></a>
            </li>
            <li className="nav-item">
              <a className="nav-link rup-nav-user rup-nav-tool-icon" href="#"><i className="fa fa-user-circle-o " aria-hidden="true"></i></a>
            </li>
            <li className="nav-item swingTop">
              <a className="nav-link rup-nav-user rup-nav-tool-icon" href="javascript:void(0)"><i className="fa fa-arrow-circle-up " aria-hidden="true"></i></a>
            </li>
          </ul>
        </div>

      </nav>

    )
  }
}
