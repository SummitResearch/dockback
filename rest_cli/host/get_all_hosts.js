var request = require('request');

if( process.argv.length != 3 ) {
    console.log( "Usage: get_all_hosts restHost" );
} else {

    var restHost = process.argv[2];

    console.log( "Getting all docker hosts on: " + restHost );

    var options = {
        uri: 'https://' + restHost + ':8443/host',
        method: 'GET',
        headers: {
            'Authorization': 'Basic ' + new Buffer('admin:Awesome123!').toString('base64')
        },
        rejectUnauthorized: false
    };

    console.log( "\n\rUsing the options:\n\r" );
    console.log( options );
    console.log( "\n\r" );

    request( options, function(error, response, body ) {

        if( error ) {
            console.log( "\n\r Got an error! \n\r" );
            console.log( error );
            return;
        }

        console.log("Got " + response.statusCode);

        if( !error ) {
            console.log( body )
        }

    });
}


