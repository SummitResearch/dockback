import {Pipe} from 'angular2/angular2';

@Pipe({
    name: 'exponentialStrength'
})
export class ExponentialStrengthPipe {
    transform( value: number, args: string[] ) : any {
        return Math.pow( value, parseInt(args[0] || '1', 10))
    }
}