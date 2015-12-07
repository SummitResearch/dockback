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
var KeyUpComponentV4 = (function () {
    function KeyUpComponentV4() {
        this.values = '';
    }
    KeyUpComponentV4 = __decorate([
        angular2_1.Component({
            selector: 'key-up4',
            template: "\n    <h4>Press [enter] or mouse away...</h4>\n    <div>\n        <input #box\n         (keyup.enter)=\"values=box.value\"\n         (blur)=\"values=box.value\">\n    </div>\n    <div>{{values}}</div>\n    "
        }), 
        __metadata('design:paramtypes', [])
    ], KeyUpComponentV4);
    return KeyUpComponentV4;
})();
exports.KeyUpComponentV4 = KeyUpComponentV4;
//# sourceMappingURL=keyup4.js.map