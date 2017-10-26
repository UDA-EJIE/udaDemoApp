import React from 'react';
import '../styles/index.scss';

import "bootstrap/dist/css/bootstrap.min.css";
import "font-awesome/css/font-awesome.css";
import "uda-rup/dist/css/rup-base.css";
import "uda-rup/dist/css/rup-theme.css";

import Header from './shared/header.jsx';
import Menu from './shared/menu.jsx';
import Footer from './shared/footer.jsx';
import Board from './board/board.jsx';

export default class App extends React.Component {
  render() {
    return (

      <div>
        <Header></Header>
        <Menu></Menu>

        <Board></Board>
        <Footer></Footer>
      </div>
    )
  }
}
