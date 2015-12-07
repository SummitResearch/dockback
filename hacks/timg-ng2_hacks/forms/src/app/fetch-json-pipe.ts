import {Pipe} from 'angular2/angular2';

@Pipe({
    name: 'fetch',
    pure: false
})
export class FetchJsonPipe {

    private fetchedValue: any;

    private fetchPromise: Promise<any>;

    transform( value: string, args: string[] ): any {

        if(!this.fetchPromise) {
            this.fetchPromise = window.fetch(value).
                then(result => result.json()).
                then(json => {
                    this.fetchedValue = json;
                    }
                );
        }

        return this.fetchedValue;
    }

}