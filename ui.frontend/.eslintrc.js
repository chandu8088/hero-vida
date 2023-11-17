module.exports = {
  parser: "@typescript-eslint/parser", // Specifies the ESLint parser
  extends: [
    "plugin:@typescript-eslint/recommended", // Uses the recommended rules from the @typescript-eslint/eslint-plugin
    "plugin:react/jsx-runtime",
    "plugin:react/recommended",
    "plugin:prettier/recommended",
  ],
  parserOptions: {
    ecmaVersion: 2018, // Allows for the parsing of modern ECMAScript features
    sourceType: "module", // Allows for the use of imports
  },
  rules: {
    curly: 1,
    "@typescript-eslint/no-this-alias": [
      "error",
      {
        allowDestructuring: false, // Disallow `const { props, state } = this`; true by default
        allowedNames: ["self"], // Allow `const self = this`; `[]` by default
      },
    ],
    "@typescript-eslint/explicit-function-return-type": [0],
    "@typescript-eslint/no-explicit-any": [0],
    '@typescript-eslint/camelcase': 'off',
    "func-style": ["error", "declaration", { allowArrowFunctions: true }],
    "import/no-extraneous-dependencies": "off",
    "import/prefer-default-export": "off",
    "jsx-a11y/anchor-is-valid": "off",
    "jsx-a11y/label-has-associated-control": "off",
    "jsx-a11y/label-has-for": "off",
    "jsx-a11y/no-autofocus": "off",
    "no-multiple-empty-lines": [
      "error",
      {
        max: 1,
        maxEOF: 1,
      },
    ],
    "max-len": [
      "error",
      {
        code: 480,
        ignoreRegExpLiterals: true,
      },
    ],
    "new-parens": 1,
    "no-bitwise": 1,
    "no-cond-assign": 1,
    "no-trailing-spaces": 0,
    "no-var": 0,
    "ordered-imports": [0],
    "object-literal-sort-keys": [0],
    "object-property-newline": ["error", { allowAllPropertiesOnSameLine: true }],
    semi: 1,
  },
  settings: {
    "react": {
    "pragma": "React",
    "version": "16.12.0"
    }
  }
};
