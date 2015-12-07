var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") return Reflect.decorate(decorators, target, key, desc);
    switch (arguments.length) {
        case 2: return decorators.reduceRight(function(o, d) { return (d && d(o)) || o; }, target);
        case 3: return decorators.reduceRight(function(o, d) { return (d && d(target, key)), void 0; }, void 0);
        case 4: return decorators.reduceRight(function(o, d) { return (d && d(target, key, o)) || o; }, desc);
    }
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var angular2_1 = require('angular2/angular2');
var exponential_strength_pipe_1 = require("./exponential-strength-pipe");
var PowerBooster = (function () {
    function PowerBooster() {
    }
    PowerBooster = __decorate([
        angular2_1.Component({
            selector: 'power-booster',
            template: "\n        <h2>Power Booster</h2>\n        <p>\n            Super power boost: {{2 | exponentialStrength: 12}}\n        </p>\n    ",
            pipes: [exponential_strength_pipe_1.ExponentialStrengthPipe]
        }), 
        __metadata('design:paramtypes', [])
    ], PowerBooster);
    return PowerBooster;
})();
exports.PowerBooster = PowerBooster;
//# sourceMappingURL=power-booster.js.map