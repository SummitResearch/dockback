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
var KeyUpComponentNG = (function () {
    function KeyUpComponentNG() {
        this.values = '';
    }
    KeyUpComponentNG.prototype.onKey = function (value) {
        this.values += value + ' | ';
    };
    KeyUpComponentNG = __decorate([
        angular2_1.Component({
            selector: 'key-up-ng',
            template: "\n\t\t<h4>Give me some more keys!</h4>\n\t\t<div><input #box (keyup)=\"onKey(box.value)\"></div>\n\t\t<div>{{values}}</div>\n\t"
        }), 
        __metadata('design:paramtypes', [])
    ], KeyUpComponentNG);
    return KeyUpComponentNG;
})();
exports.KeyUpComponentNG = KeyUpComponentNG;
//# sourceMappingURL=keyup2.js.map