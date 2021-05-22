package com.sledz.services.ProductProvider;

/**
 * Ten interferjs zawiera funkcje pozwalające na dostęp do api Providerów
 */
public interface ExternalProductProviderAuthenticator {
    /**
     * Ta funkcja powinna zawsze zwracać ważny token do api providera
     * Najlepiej gdyby token był cachowany i odnawiany tylko w razie potrzeby
     * @return Ważny token do api providera
     */
    default String getToken() {
        return "ababababababababababababababababababab"; //dummy token
    }
}
