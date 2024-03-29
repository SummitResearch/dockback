var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
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