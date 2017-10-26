import React from 'react';
import '../../styles/shared/header.scss';

export default class Header extends React.Component {
  render() {
    return (
      <header>
        <div id="cabecera" className="cabecera" >
        	<div className="header-left" >
        		<img src="assets/images/ejie.jpg"/>
        	</div>
        	<a href="/x21aModulesWar/" className="header-rigth">
        		<img src="assets/images/euskadieus_logo.gif" alt="Euskadi Eus"/>
        	</a>
        </div>
      </header>

    )
  }
}
