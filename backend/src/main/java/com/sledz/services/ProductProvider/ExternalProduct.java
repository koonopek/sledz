package com.sledz.services.ProductProvider;

import java.math.BigDecimal;

/**
 * Ten interfejs reprezentuje dane o produkcie które możemy pozyskać z zewnętrznego żródła produktów.
 * Reprezentuje on wszystkie które odczytujemy ze żródła - nie wszystkie są kluczowe.
 * <p>
 * Przy implementacji wsparcia dla dostawcy produktów
 * należy zaimplementować te funkcje interfejsu które uznamy za pożyteczne
 * </p>
 */
public interface ExternalProduct {
    default Integer getExternalId()
    {
        return null;
    }
    /**
     * Cena produktu w PLN
     * @return Cena produktu PLN albo null jeżeli nie wspierana
     */
    default BigDecimal getPrice() {
        return null;
    }

    /**
     * Nazwa produktu podana przez providera
     * @return nazwa produktu lub null jeżeli nie wspierana
     */
    default String getName() {
        return null;
    }

    /**
     * Id kategorii w systemie providera
     * @return id lub null jeżeli nie wspierana
     */
    default Integer getCategoryId() {
        return null;
    }

    /**
     * Czytelna nazwa kategorii
     * @return nazwa lub null jeżeli nie wspierana
     */
    default String getCategoryName() {
        return null;
    }

    /**
     * URL do obrazka reprezentującego produkt
     * @return url lub null jeżeli nie wspierana
     */
    default String getImageUrl() {
        return null;
    }
}
