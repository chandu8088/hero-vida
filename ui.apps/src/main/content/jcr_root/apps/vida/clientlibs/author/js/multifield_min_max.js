(function($, Coral) {
    "use strict";

    var registry = $(window).adaptTo("foundation-registry");

    // Validator for required for multifield max and min items
    registry.register("foundation.validation.validator", {
        selector: "[data-validation=multifield-item-number]",
        validate: function(element) {
            var el = $(element);
            let max=el.data("max-items");
            let min=el.data("min-items");
            let items=el.children("coral-multifield-item").length;
            let domitems=el.children("coral-multifield-item");
            console.log("{} : {} :{} ",max,min,items);
            if(items>max){
              return "You can add maximum "+max+" items."
            }
            if(items<min){
                return "Please add minimum "+min+" items."
            }
        }
    });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=onebkk-formid-validation]",
        validate: function(element) {
            let el = $(element);
            let pattern = /[^a-zA-Z0-9 ]/;
            let value = el.val();
            if(pattern.test(value)) {
                return "Special characters are not allowed"
            }
        }
    });
    

   
})(jQuery, Coral);