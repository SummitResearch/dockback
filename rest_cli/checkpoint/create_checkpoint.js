var request = require('request');

if( process.argv.length != 5 ) {
    console.log( "Usage: create_host restHost dockerHostId containerId " );
} else {

    var restHost = process.argv[2];
    var dockerHostId = process.argv[3];
    var containerId = process.argv[4];

    console.log( "Creating new checkpoint for: " + containerId);

    var options = {
        uri: 'https://' + restHost + ':8443/host/' + dockerHostId + '/container/' + containerId + '/checkpoint',
        method: 'POST',
        //json: {
        //    "name": name,
        //    "description": description,
        //    "schedule": schedule
        //},
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


