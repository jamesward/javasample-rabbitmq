## Howto

        heroku create --stack cedar
        heroku config:add RABBITMQ_URL='[the rabbigmq url]'
        git push heroku master
        heroku scale send+1 springsend+1 rec+1 springrec+1
        heroku logs --tail
        heroku scale send-1 springsend-1 rec-1 springrec-1
