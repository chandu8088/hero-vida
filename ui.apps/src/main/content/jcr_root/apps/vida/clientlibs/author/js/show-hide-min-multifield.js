/**
 * Register required field validation.
 *
 * The validator keys off of the ".acs-commons-field-required-allow-hidden" selector. To use in Touch UI
 * dialogs, add the class "field-required" to a textfield. It is implemented to
 * get around the problem that a hidden required field cannot be submitted. The
 * default AEM validator validates the field even if it is hidden.
 *
 */
 (function($, Granite) {
    "use strict";
  
    $(window)
        .adaptTo("foundation-registry")
        .register(
            "foundation.validation.validator",
            {
              selector : ".acs-commons-field-required-allow-hidden-multifield",
              validate : function(element) {
                  var el = $(element);
                let min=el.data("min-items");
                let max=el.data("max-items");
                let items=el.children("coral-multifield-item").length;
                let domitems=el.children("coral-multifield-item");
                console.log("{} :{} ",min,items);
				        console.log("{} :{} ",max,items);
                console.log("entering the function");
                var field, value;
  
                field = $(el).closest(".coral-Form-field");
                value = $(el).val();
  
                // check if the field or or its parent is hidden
                var hidden = $(field).hasClass('hide') || $(field).closest('.hide').length;
  
                // if field or its parent not hidden, validate
                if ((!hidden && (value === null || value === '')) && (items<min) ){
                  return Granite.I18n.get("Please add minimum "+min+" items."); 
                }
                if ((!hidden && (value === null || value === '')) && (items>max) ){
                  return Granite.I18n.get("Please don't add more than "+max+" items."); 
                }
                return null;
              },
              clear : function(el, ctx) {
                var field = $(el).closest(".coral-Form-field");
  
                field.removeAttr("aria-invalid").removeClass("is-invalid");
  
                field.nextAll(".coral-Form-fielderror").tooltip("hide").remove();
                field.nextAll(".coral-Form-fieldinfo").removeClass("u-coral-screenReaderOnly");
              }
            });
  })(Granite.$, Granite);