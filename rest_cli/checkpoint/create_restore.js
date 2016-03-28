var request = require('request');

if( process.argv.length != 6 ) {
    console.log( "Usage: create_host restHost dockerHostId containerId checkpointId" );
} else {

    var restHost = process.argv[2];
    var dockerHostId = process.argv[3];
    var containerId = process.argv[4];
    var checkpointId = process.argv[5];

    console.log( "Creating new restore for: " + containerId);

    var options = {
        uri: 'https://' + restHost + ':8443/checkpoint/' + checkpointId + '/restore',
        method: 'POST',
        json: {
           "hostId": dockerHostId,
           "containerId": containerId
        },
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


