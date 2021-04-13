# Allegro api

[Snippety](allegro_api.py)

## Autoryzacja
* Należy zerejstrowac aplikację - https://apps.developer.allegro.pl/ -> Client ID oraz Client Secret
  * Client ID oraz Client Secret są niezbędne do komunikacji w ramach protokołu OAuth, który - w wersji 2.0 - Allegro REST API obsługuje w standardzie. Tokeny dostępowe są zgodne ze standardem JWT.
* Intersujący na flow korzystania z aplikacji - https://developer.allegro.pl/auth/#clientCredentialsFlow
  * Tym sposobem mamy dostęp tylko do danych publicznych a właśnie na takich nam zależy
* Wywołanie zasobu z użyciem tokena https://developer.allegro.pl/auth/#resource
* Przedłużenia ważności tokena https://developer.allegro.pl/auth/#refresh-token
```python
def get_token(client_id: str, client_secret: str):
    token = base64.b64encode(f'{client_id}:{client_secret}'.encode('ascii')).decode('ascii')
    headers = {'Authorization': f'Basic {token}'}
    print(headers)
    res = post('https://allegro.pl/auth/oauth/token?grant_type=client_credentials', headers=headers)

    return res.json()
```
## Przeglądanie katalogu produktów
> To api będzie dostępne tylko do pierwszego czerwca :( potem wymagana jest weryfikacja
* Dokumentacja źródła:
  *  https://developer.allegro.pl/redoc/redoc-static.html#operation/getListing
  *  https://developer.allegro.pl/en/listing/
* Jest możliwość filtrowania po kategorii oraz frazie czyli tak jak w WEB GUI
```python
def allegro_get(path: str):
    headers = {
        'Authorization' : f'Bearer {jwt}',
        'Accept' : 'application/vnd.allegro.public.v1+json'
    }
    
    res = get('https://api.allegro.pl' + path, headers=headers)

    return res.json()

response = allegro_get('/offers/listing?phrase=One Plus 5T&limit=10')
```
* Przykładowa odpowiedz [plik](one_plus_5t_by_phrase.json)

