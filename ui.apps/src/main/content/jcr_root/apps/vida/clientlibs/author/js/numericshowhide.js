/**
 * Extension to the standard dropdown/select component. It enabled hidding/unhidding of other components based on the
 * selection made in the dropdown/select in multifield and multivalued.
 *
 * How to use:
 *
 * - add the class cq-dialog-dropdown-showhide-multival to the dropdown/select element
 * - add the data attribute cq-dialog-dropdown-showhide-target to the dropdown/select element, value should be the
 *   selector, usually a specific class name, to find all possible target elements that can be shown/hidden.
 * - add the target class to each target component that can be shown/hidden
 * - add the class hidden to each target component to make them initially hidden
 * - add the attribute showhidetargetvalue to each target component, the value(or commma separated multiple values e.g. val1, val2) should equal the value of the select
 *   option that will unhide this element.
 */
(function(document, $) {
    "use strict";

    // when dialog gets injected
    $(document).on("foundation-contentloaded", function(e) {
        // if there is already an inital value make sure the according target element becomes visible
        showHideHandler($(".cq-dialog-field-showhide-multival", e.target));
    });

    $(document).on("change", ".cq-dialog-field-showhide-multival", function(e) {
        showHideHandler($(this));
    });

    function showHideHandler(el) {
        el.each(function(i, element) {
            var length = $(element).find('.cq-dialog-enable-numeric').length;
            var enableNumeric = $(element).find('.cq-dialog-enable-numeric');
            for(var i=0; i<length; i++){
                if(enableNumeric[i].checked){
                    $(".cq-dialog-field-showhide-multival").find('.enable-decimal')[i].disabled=true;
                    $(".cq-dialog-field-showhide-multival").find('.enable-numeric')[i].disabled=false;
                }
                else{
                    $(".cq-dialog-field-showhide-multival").find('.enable-decimal')[i].disabled=false;
                    $(".cq-dialog-field-showhide-multival").find('.enable-numeric')[i].disabled=true;
                }
            }
			console.log(length);
        })
    }
})(document, Granite.$);