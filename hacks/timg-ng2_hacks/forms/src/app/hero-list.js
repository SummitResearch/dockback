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
var fetch_json_pipe_1 = require("./fetch-json-pipe");
var HeroList = (function () {
    function HeroList() {
    }
    HeroList = __decorate([
        angular2_1.Component({
            selector: 'hero-list',
            template: "\n        <h4>Heroes from JSON File</h4>\n\n        <div *ng-for=\"#hero of ('heroes.json' |  fetch)\">\n            {{hero.name}}\n        </div>\n\n        <p>Heroes as JSON:\n            {{'heroes.json' | fetch | json}}\n        </p>\n    ",
            directives: [angular2_1.CORE_DIRECTIVES],
            pipes: [fetch_json_pipe_1.FetchJsonPipe]
        }), 
        __metadata('design:paramtypes', [])
    ], HeroList);
    return HeroList;
})();
exports.HeroList = HeroList;
//# sourceMappingURL=hero-list.js.map