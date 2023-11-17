import analyticsUtils from "../../../site/scripts/utils/analyticsUtils";

function trackScroll(scrolled) {
  analyticsUtils.trackPageScroll(Math.trunc(scrolled));
}

function getPercentageViewable(element) {
  const rect = element.getBoundingClientRect();
  const percentage = (100 / rect.height) * (window.innerHeight - rect.top);
  return percentage === Infinity ? 0 : percentage;
}

export function getScrollTracker(scrollDepths) {
  try {
    let handleScroll;
    if (scrollDepths && scrollDepths.length > 0) {
      handleScroll = () => {
        const percentage = getPercentageViewable(document.body);
        console.log("<<< scroll percentage", percentage);
        if (percentage >= scrollDepths[0]) {
          if (analyticsUtils.isAnalyticsEnabled()) {
            console.log("<<< scroll percentage passed", scrollDepths[0]);
            trackScroll(scrollDepths[0]);
          }
          scrollDepths.shift();
        }
      };
      console.log("<<< scroll event addition");
      document.addEventListener("scroll", handleScroll);
    } else {
      console.log("<<< scroll event removal");
      window.removeEventListener("scroll", handleScroll);
    }
  } catch (error) {
    //  Block of code to handle errors
    console.log(error);
  }
}
