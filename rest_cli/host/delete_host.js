var request = require('request');

if( process.argv.length != 4 ) {
    console.log( "Usage: delete_host restHost dockerHostId" );
} else {

    var restHost = process.argv[2];
    var dockerHostId = process.argv[3];

    console.log( "Deleting docker host on: " + restHost + " with id: " + dockerHostId );

    var options = {
        uri: 'https://' + restHost + ':8443/host/' + dockerHostId,
        method: 'DELETE',
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


