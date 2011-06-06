## Howto

        heroku create --stack cedar
        heroku config:add LANGUAGE_PACK_URL='https://user:pass@github.com/heroku/language-pack-java.git'
        heroku addons:upgrade logging:expanded
        heroku addons:add rabbitmq
        git push heroku master
        heroku scale send+1 springsend+1 rec+1 springrec+1
        heroku logs --tail
        heroku scale send-1 springsend-1 rec-1 springrec-1
