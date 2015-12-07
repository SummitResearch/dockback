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
var PowerBoostCalculator = (function () {
    function PowerBoostCalculator() {
        this.power = 5;
        this.factor = 1;
    }
    PowerBoostCalculator = __decorate([
        angular2_1.Component({
            selector: 'power-boost-calculator',
            template: "\n        <h2>Power Boost Calculator</h2>\n        <div>Normal power: <input [(ng-model)]=\"power\"></div>\n        <div>Boost factor: <input [(ng-model)]=\"factor\"></div>\n        <p>\n            Super Hero Power: {{power | exponentialStrength: factor}}\n        </p>\n    ",
            pipes: [exponential_strength_pipe_1.ExponentialStrengthPipe],
            directives: [angular2_1.FORM_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [])
    ], PowerBoostCalculator);
    return PowerBoostCalculator;
})();
exports.PowerBoostCalculator = PowerBoostCalculator;
//# sourceMappingURL=power-boost-calculator.js.map