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
var hero_1 = require('./hero');
var keyup_1 = require('./keyup');
var loopback_1 = require('./loopback');
var keyup2_1 = require("./keyup2");
var keyup3_1 = require("./keyup3");
var keyup4_1 = require("./keyup4");
var AppComponent = (function () {
    function AppComponent() {
        this.heroes = HEROES;
        this.title = 'Tour of Heroes';
        this.hero = {
            id: 1,
            name: 'Windstorm'
        };
        this.title = 'Awesome List of Heroes';
    }
    AppComponent.prototype.onSelect = function (hero) {
        this.selectedHero = hero;
    };
    AppComponent.prototype.getSelectedClass = function (hero) {
        return { 'selected': hero === this.selectedHero };
    };
    AppComponent.prototype.onClickMe = function () {
        console.log("clicked!");
    };
    AppComponent.prototype.addHero = function (newHero) {
        if (newHero.value) {
            this.heroes.push(new hero_1.Hero(this.heroes.length + 1, newHero.value));
            newHero.value = null;
        }
    };
    AppComponent = __decorate([
        angular2_1.Component({
            selector: 'my-app',
            template: "\n\t\t<h1>{{title}}</h1>\n\t\t<div *ng-if=\"selectedHero\">\n\t\t\t<h2>My favorite hero is:  {{selectedHero.name}}\n\t\t</div>\n\t\t<p *ng-if=\"heroes.length > 3\">There are a bunch of heroes!</p>\n\t\t<h2>My Heroes</h2>\n\t\t<ul class=\"heroes\">\n\t\t\t<li *ng-for=\"#hero of heroes\"\n\t\t\t[ng-class]=\"getSelectedClass(hero)\" \n\t\t\t(click)=\"onSelect(hero)\">\n\t\t\t<span class=\"badge\">{{hero.id}}</span> {{hero.name}}\n\t\t\t</li>\n\t\t</ul>\n\t\t<div *ng-if=\"selectedHero\">\n\t\t\t<h2>{{selectedHero.name}} details!</h2>\n\t\t\t<div><label>id: </label>{{selectedHero.id}}</div>\n\t\t\t<div>\n\t\t\t\t<label>name: </label>\n\t\t\t\t<div><input [(ng-model)]=\"selectedHero.name\" placeholder=\"name\"></div>\n\t\t\t</div>\n\t\t</div>\n\t\t\n\t\t<input #new-hero\n\t\t(keyup.enter)=\"addHero(newHero)\"\n\t\t(blur)=\"addHero(newHero)\">\n\t\t<button (click)=\"addHero(newHero)\">Add</button>\n\n\t\t<key-up>testing...</key-up>\n\t\t\n\t\t<loop-back>loopback...</loop-back>\n\n\t\t<key-up-ng>key testing 2...</key-up-ng>\n\n\t\t<key-up3>key testing 3...</key-up3>\n\n        <key-up4>key testing 4...</key-up4>\n\t\t",
            directives: [angular2_1.CORE_DIRECTIVES, angular2_1.FORM_DIRECTIVES, keyup_1.KeyUpComponent, loopback_1.LoopbackComponent, keyup2_1.KeyUpComponentNG, keyup3_1.KeyUpComponentV3, keyup4_1.KeyUpComponentV4],
            styles: ["\n\t\t.heroes {list-style-type: none; margin-left: 1em; padding: 0; width: 10em;}\n\t\t.heroes li { cursor: pointer; position: relative; left: 0; transition: all 0.2s ease; }\n\t\t.heroes li:hover {color: #369; background-color: #EEE; left: .2em;}\n\t\t.heroes .badge {\n\t\t\tfont-size: small;\n\t\t\tcolor: white;\n\t\t\tpadding: 0.1em 0.7em;\n\t\t\tbackground-color: #369;\n\t\t\tline-height: 1em;\n\t\t\tposition: relative;\n\t\t\tleft: -1px;\n\t\t\ttop: -1px;\n\t\t}\n\t\t.selected { background-color: #EEE; color: #369; }\n  "]
        }), 
        __metadata('design:paramtypes', [])
    ], AppComponent);
    return AppComponent;
})();
angular2_1.bootstrap(AppComponent);
var HEROES = [
    new hero_1.Hero(11, "Mr. Nicer"),
    { "id": 12, "name": "Narco" },
    { "id": 13, "name": "Bombasto" },
    { "id": 14, "name": "Celeritas" },
    { "id": 15, "name": "Magneta" },
    { "id": 16, "name": "RubberMan" },
    { "id": 17, "name": "Dynama" },
    { "id": 18, "name": "Dr IQ" },
    { "id": 19, "name": "Magma" },
    { "id": 20, "name": "Tornado" }
];
//# sourceMappingURL=app.js.map