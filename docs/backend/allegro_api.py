from requests import *
import json
import base64

CLIENT_SECRET = '***'
CLIENT_ID = '***'

def get_token(client_id: str, client_secret: str):
    token = base64.b64encode(f'{client_id}:{client_secret}'.encode('ascii')).decode('ascii')
    headers = {'Authorization': f'Basic {token}'}
    print(headers)
    res = post('https://allegro.pl/auth/oauth/token?grant_type=client_credentials', headers=headers)

    return res.json()

jwt = get_token(client_id=CLIENT_ID,client_secret=CLIENT_SECRET)['access_token']


def allegro_get(path: str):
    headers = {
        'Authorization' : f'Bearer {jwt}',
        'Accept' : 'application/vnd.allegro.public.v1+json'
    }
    
    res = get('https://api.allegro.pl' + path, headers=headers)

    return res.json()

response = allegro_get('/offers/listing?phrase=One Plus 5T&limit=10')
