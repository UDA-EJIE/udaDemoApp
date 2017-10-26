import React from 'react';
import '../../styles/shared/footer.scss';

export default class Footer extends React.Component {
  render() {
    return (
      <footer>
        <div className="footer-row-1">
          <span className="footer-informacion-legal"> Aviso legal</span>
          <span className="footer-ejgv">Eusko Jaurlaritza - Gobierno Vasco</span>
        </div>
        <div className="footer-row-2">
          <img className="footer-image" src="/assets/images/web01-2014_claim_pertsona_helburu_es.gif" />
        </div>
      </footer>

    )
  }
}
