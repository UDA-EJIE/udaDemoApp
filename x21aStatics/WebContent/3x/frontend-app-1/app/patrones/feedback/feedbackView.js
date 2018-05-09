;
'use-strict'

import {qs, qsa, $on, $parent, $delegate} from 'app/common/helpers';

import { RupFeedback } from 'rup/rup.feedback';


class FeedbackView {
  constructor() {
    
    this.$feedbackOk = qs('#feedbackOk');
    this.$feedbackAlert = qs('#feedbackAlert');
    this.$feedbackError = qs('#feedbackError');
    
    $(this.$feedbackOk).rup_feedback({
        message:"<strong>Ok!</strong> Se ha realizado correctamente la acción solicitada.",
        type:"ok"
    });
    
    $(this.$feedbackAlert).rup_feedback({
        message:"<strong>Ok!</strong> Se ha realizado correctamente la acción solicitada.",
        type:"alert"
    });
    
    $(this.$feedbackError).rup_feedback({
        message:"<strong>Ok!</strong> Se ha realizado correctamente la acción solicitada.",
        type:"error"
    });
    
   
  }

  render() {
	  console.log("render");
  }
  
}

export { FeedbackView };
