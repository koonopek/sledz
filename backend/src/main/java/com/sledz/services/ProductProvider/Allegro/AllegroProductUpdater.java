package com.sledz.services.ProductProvider.Allegro;

import com.sledz.entities.Product;
import com.sledz.entities.Value;
import com.sledz.repositories.ProductRepository;
import com.sledz.services.ProductProvider.ExternalProductProvider;
import com.sledz.services.ProductProvider.ProductProvider;
import com.sledz.services.ProductProvider.ProductQuery;
import com.sledz.services.ProductProvider.ProductUpdater;
import com.sledz.utils.*;

import java.util.*;

public class AllegroProductUpdater implements ProductUpdater {
    ProductRepository _repo;
    ExternalProductProvider _provider;
    Map<Long,ProductQuery> queries = new HashMap<>();
    static Timer updateTimer = new Timer("UpdateProducts",true);

    AllegroProductUpdater(ProductRepository repo, ExternalProductProvider provider)
    {
        setProvider(provider);
        setRepository(repo);
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateAll();
            }
        },10000,1000*60*60*24);
    }

    @Override
    public void setRepository(ProductRepository repo) {
        _repo = repo;
    }

    @Override
    public void setProvider(ExternalProductProvider provider) {
        _provider = provider;
    }

    @Override
    public Product addNewProduct(ProductQuery query) {

        var exProdList = _provider.searchProducts(ProductQuery.builder().
                querySize(10).
                phrase(query.phrase).
                categoryId(query.categoryId).
                build());

        var prod = ProductProcessor.convert(exProdList,_repo,_provider);
        _repo.save(prod);
        return prod;
    }

    @Override
    public void updateProduct(long productId) {
        var popt = _repo.findById(productId);
        if(popt.isEmpty()) return;

        var p = popt.get();

        var exProdList = _provider.searchProducts(ProductQuery.builder()
                .querySize(10)
                .phrase(p.name).categoryId(p.category.externalId).build());

        var priceMean = Statistic.average(exProdList);

        var v = new Value();
        v.date = Calendar.getInstance().getTimeInMillis();
        v.value = priceMean;
        p.valueHistory.add(v);

        _repo.save(p);
    }

    public void updateAll()
    {
        var prods = _repo.findAll();

        for(var prod : prods)
        {
            updateProduct(prod.id);
        }
    }


}
