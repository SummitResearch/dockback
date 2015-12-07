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
var KeyUpComponentV3 = (function () {
    function KeyUpComponentV3() {
        this.values = '';
    }
    KeyUpComponentV3 = __decorate([
        angular2_1.Component({
            selector: 'key-up3',
            template: "\n        <h4>Type away! Press [enter] when you're done.</h4>\n        <div><input #box (keyup.enter)=\"values=box.value\"></div>\n        <div>{{values}}</div>\n    "
        }), 
        __metadata('design:paramtypes', [])
    ], KeyUpComponentV3);
    return KeyUpComponentV3;
})();
exports.KeyUpComponentV3 = KeyUpComponentV3;
//# sourceMappingURL=keyup3.js.map