package ddog.persistence.mysql.adapter;

import ddog.domain.shop.BeautyShop;
import ddog.domain.shop.port.BeautyShopPersist;
import ddog.persistence.mysql.jpa.entity.BeautyShopJpaEntity;
import ddog.persistence.mysql.jpa.repository.BeautyShopJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BeautyShopRepository implements BeautyShopPersist {

    private final BeautyShopJpaRepository beautyShopJpaRepository;

    @Override
    public List<BeautyShop> findBeautyShopsByAddress(String address) {
        List<BeautyShopJpaEntity> beautyShops = beautyShopJpaRepository.findBeautyShopsByShopAddress(address);

        return beautyShops.stream()
                .map(BeautyShopJpaEntity::toModel)
                .collect(Collectors.toList());
    }

    public List<BeautyShop> findBeautyShopsByAddressPrefix(String addressPrefix) {
        List<BeautyShopJpaEntity> beautyShops = beautyShopJpaRepository.findBeautyShopsByAddressPrefix(addressPrefix);

        return beautyShops.stream()
                .map(BeautyShopJpaEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public BeautyShop findBeautyShopById(Long shopId) {
        return beautyShopJpaRepository.findByShopId(shopId).get().toModel();
    }

}