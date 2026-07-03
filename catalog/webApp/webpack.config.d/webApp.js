config.performance = false;
config.resolve = config.resolve || {};
config.resolve.fallback = Object.assign({}, config.resolve.fallback, {
    os: false,
    path: false,
});
config.module = config.module || {};
config.module.rules = (config.module.rules || []).filter((rule) => rule.enforce !== "pre");
config.module.parser = Object.assign({}, config.module.parser, {
    javascript: Object.assign({}, config.module.parser && config.module.parser.javascript, {
        importMeta: false,
    }),
});
config.devtool = false;
// Minifying the multi-megabyte Kotlin/JS bundle takes over 10 minutes; the catalog
// is a demo app, so skip optimization entirely in favor of build time.
config.optimization = config.optimization || {};
config.optimization.minimize = false;
