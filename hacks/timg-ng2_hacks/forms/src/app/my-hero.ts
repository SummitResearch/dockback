import {Component} from 'angular2/angular2';

@Component({
    selector: 'my-hero',
    template: 'Message: {{ delayedMessage | async }}'
})
export class MyHeroAsyncMessageComponent {
    delayedMessage:Promise<string> = new Promise((resolve, reject) => {
        setTimeout(() => resolve('You are my Hero!'), 5000);
    });
}