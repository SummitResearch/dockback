var restangular = require('restangular');
var angular = require('angular');

if( process.argv.length != 3 ) {
    console.log( "Usage: get_host restHost dockerHostId" );
} else {

    var restHost = process.argv[2];

    console.log( "Getting all docker hosts on: " + restHost );

    restangular.allUrl('host', 'https://' + restHost + ':8443').getList();

}


