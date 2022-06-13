package com.kakao.cafe.point.repository;

import com.kakao.cafe.point.domain.PointWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointWalletRepository extends JpaRepository<PointWallet, Long> {
}
