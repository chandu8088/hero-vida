/* eslint-disable */
const webpack = require("webpack");
const merge = require("webpack-merge");
const common = require("./webpack.common.js");
const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const HtmlWebpackPartialsPlugin = require("html-webpack-partials-plugin");
const ScriptExtHtmlWebpackPlugin = require("script-ext-html-webpack-plugin");

const SOURCE_ROOT = __dirname + "/src/main/webpack";

const envPlugin = new webpack.EnvironmentPlugin({
  NODE_ENV: "development",
  DEBUG: true
});

/* Static files to render full template */
const htmlStaticPages = [
  "index.html",
  "login.html",
  "profile.html",
  "sample.html",
  "faq.html",
  "pre-booking.html",
  "map.html",
  "test-drive.html",
  "test-drive-selector.html",
  "test-drive-short-term.html",
  "test-drive-long-term.html",
  "test-drive-long-term-new.html",
  "test-drive-long-term-status.html",
  "advantage.html",
  "battery.html",
  "test-drive-long-term-summary.html",
  "test-drive-long-term-upload-documents.html",
  "prebooking-payment-status.html",
  "purchase-configurator.html",
  "contact-us.html",
  "product-configurator.html",
  "billing-pricing-details.html",
  "billing-pricing-details-new.html",
  "booking-payment-status.html",
  "billing-shipping-details.html",
  "product-details.html",
  "upload-documents.html",
  "technology.html",
  "charging.html",
  "delivery-tracker.html",
  "nominee-details.html",
  "about-us.html",
  "aadharValidation.html",
  "aadharValidationStatus.html",
  "experience-centre.html",
  "service-centre.html",
  "otppage.html",
  "page-not-found.html",
  "configuration.html",
  "home.html",
  "listing.html",
  "product-details-new.html",
  "quick-reserve.html",
  "quick-purchase.html",
  "aadhar-verification.html",
  "internal-user.html",
  "emi-calculator.html",
  "partial-payment.html",
  "dealer-locator.html",
  "offers.html",
  "scooter-variants.html",
  "test-drive-login.html"
];
const multipleHtmlPlugins = htmlStaticPages.map((name) => {
  return new HtmlWebpackPlugin({
    template: path.resolve(__dirname, SOURCE_ROOT + `/static/${name}`),
    custom: `<script src="/src/main/webpack/static/app-config.js"></script>`,
    filename: `${name}`,
    cache: false
  });
});

/* Partials to render inside the static files */
const htmlPartials = [
  {
    name: "header"
  },
  {
    name: "footer"
  },
  {
    name: "faq"
  },
  {
    name: "tabs"
  },
  {
    name: "accordion"
  },
  {
    name: "app-cards"
  },
  {
    name: "banner"
  },
  {
    name: "advantage-card"
  },
  {
    name: "feature-banner"
  },
  {
    name: "two-column-text"
  },
  {
    name: "product-specs"
  },
  {
    name: "product-info-tiles"
  },
  {
    name: "rich-text"
  },
  {
    name: "banner-carousel"
  },
  {
    name: "card-details"
  },
  {
    name: "rounded-carousel"
  },
  {
    name: "two-column-card"
  },
  {
    name: "advanced-accordion"
  },
  {
    name: "social-feed"
  },
  {
    name: "full-screen-video"
  },
  {
    name: "text-asset"
  },
  {
    name: "product-info"
  },
  {
    name: "configurator"
  },
  {
    name: "asset-collage"
  },
  {
    name: "highlight-card-carousel"
  },
  {
    name: "vertical-slider"
  },
  {
    name: "asset-banner"
  },
  {
    name: "floating-icon"
  },
  {
    name: "scroll-navigation"
  },
  {
    name: "navigation"
  },
  {
    name: "multi-cards"
  },
  {
    name: "asset-banner-carousal"
  },
  {
    name: "info-banner"
  },
  {
    name: "info-graphics"
  },
  {
    name: "awards-carousel"
  },
  {
    name: "text-image-banner"
  },
  {
    name: "full-bleed-banner"
  },
  {
    name: "vertical-cards"
  },
  {
    name: "video-banner"
  },
  {
    name: "news-cards"
  },
  {
    name: "text-image-card"
  },
  {
    name: "full-bleed-image"
  },
  {
    name: "leadership-cards"
  },
  {
    name: "service-banner"
  },
  {
    name: "full-image-banner"
  },
  {
    name: "text-asset-light"
  },
  {
    name: "leave-page-notification"
  },
  {
    name: "ec-hotspot"
  },
  {
    name: "page-not-found"
  },
  {
    name: "terms-and-conditions"
  },
  {
    name: "product-banner"
  },
  {
    name: "banner-notification"
  },
  {
    name: "hero-banner"
  },
  {
    name: "hero-simple-banner"
  }
  ,
  {
    name: "listing"
  },
  {
    name: "blog-card"
  },
  {
    name: "search"
  },
  {
    name: "single-article"
  },
  {
    name: "listing-back"
  },
  {
    name: "top-bar"
  },
  {
    name: "card-banner"
  },
  {
    name: "step-list"
  },
  {
    name: "skewed-strip"
  },
  {
    name: "options-list"
  },
  {
    name: "list-items"
  },
  {
    name: "dashed-headers"
  },
  {
    name: "logo-graphics"
  },
  {
    name: "video-carosuel"
  },
  {
    name: "left-border-box"
  },
  {
    name: "button-tabs"
  }
];

const multipleHtmlPartials = htmlPartials.map((component) => {
  return new HtmlWebpackPartialsPlugin({
    path: path.join(
      SOURCE_ROOT + `/components/${component.name}/${component.name}.html`
    ),
    location: component.tag || component.name,
    template_filename: htmlStaticPages,
    options: {
      ...component.options
    }
  });
});

const scriptExt = new ScriptExtHtmlWebpackPlugin({
  defaultAttribute: "defer"
});

module.exports = (env) => {
  const writeToDisk = env && Boolean(env.writeToDisk);

  return merge(common, {
    mode: "development",
    devtool: "inline-source-map",
    performance: {
      maxAssetSize: 1000000,
      maxEntrypointSize: 1000000,
      hints: "warning",
      assetFilter: function (assetFilename) {
        return ![".html", ".css", ".js", ".png", ".gif", ".jpg"].some(function (
          suffix
        ) {
          return assetFilename.endsWith(suffix);
        });
      }
    },
    plugins: [envPlugin].concat(
      multipleHtmlPlugins,
      multipleHtmlPartials,
      scriptExt
    ),
    devServer: {
      inline: true,
      proxy: [
        {
          context: ["/content", "/etc.clientlibs"],
          target: "http://localhost:4502"
        }
      ],
      writeToDisk,
      liveReload: !writeToDisk
    }
  });
};
