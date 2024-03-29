import {Component} from 'angular2/angular2';
import {ExponentialStrengthPipe} from "./exponential-strength-pipe";

@Component({
    selector: 'power-booster',
    template: `
        <h2>Power Booster</h2>
        <p>
            Super power boost: {{2 | exponentialStrength: 12}}
        </p>
    `,
    pipes: [ExponentialStrengthPipe]
})
export class PowerBooster {}