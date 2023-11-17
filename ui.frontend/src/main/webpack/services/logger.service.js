import Logger from "js-logger";

Logger.useDefaults({
  formatter: function (messages) {
    messages.unshift("::");
    messages.unshift(new Date().toUTCString());
  }
});

/* Send messages to a custom logging endpoint for analysis
Logger.setHandler(function (messages, context) {
    // Send messages to a custom logging endpoint for analysis.
});
*/

if (process.env.NODE_ENV === "production") {
  Logger.setLevel(Logger.WARN);
}

export default Logger;
