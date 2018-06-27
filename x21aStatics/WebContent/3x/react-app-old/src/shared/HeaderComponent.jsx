import React from 'react';

class HeaderComponent extends React.Component {

  constructor(props) {
    super(props);

  }

  render() {
    return (
      <header>
        <div id="cabecera" class="cabecera" >
        	<div style="float: left;" >
        		<img src="assets/images/ejie.jpg"/>
        	</div>
        	<a href="/x21aModulesWar/" style="float: right;">
        		<img src="assets/images/euskadieus_logo.gif" alt="Euskadi Eus"/>
        	</a>
        </div>
      </header>
      

    );
  }

}

export default HeaderComponent;
