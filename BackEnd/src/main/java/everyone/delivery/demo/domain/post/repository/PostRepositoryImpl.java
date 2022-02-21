package everyone.delivery.demo.domain.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto.KeyColumn;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import static everyone.delivery.demo.domain.post.QPostEntity.postEntity;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostQueryDSLRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Slice<PostEntity> getPagedList(PostSearchDto postSearchDto) {
        QueryResults<?> queryResults = jpaQueryFactory.from(postEntity)
                .where(getWhereBuilder(postSearchDto))
                .orderBy(getOrderSpecifier(postSearchDto))
                .offset(postSearchDto.getOffset())
                .limit(postSearchDto.getLimit() + 1)
                .fetchResults();

        return new SliceImpl(queryResults.getResults(),PageRequest.of(postSearchDto.getOffset(),postSearchDto.getLimit()),true);
    }

    private OrderSpecifier getOrderSpecifier(PostSearchDto postSearchDto){
        if(postSearchDto.getKeyColumn().equals(KeyColumn.REG_DATE)){
            if(postSearchDto.isASC())
                return postEntity.regDate.asc();
            else
                return postEntity.regDate.desc();
        }
        else {
            if(postSearchDto.isASC())
                return postEntity.updateDate.asc();
            else
                return postEntity.updateDate.desc();
        }
    }

    private BooleanBuilder getWhereBuilder(PostSearchDto postSearchDto){
        BooleanBuilder builder = new BooleanBuilder();
        for(Long posterId :ListUtils.emptyIfNull(postSearchDto.getPosterIdList())){
            builder.or(postEntity.postId.eq(posterId));
        }

        if (!StringUtils.isEmpty(postSearchDto.getTitle())) {
            builder.and(postEntity.title.contains(postSearchDto.getTitle()));
        }

        for(String address :ListUtils.emptyIfNull(postSearchDto.getAddresses())){
            builder.or(postEntity.addresses.contains(address));
        }

        if(postSearchDto.getKeyColumn().equals(KeyColumn.REG_DATE)){
            if (!StringUtils.isEmpty(postSearchDto.getStartDate())) {
                builder.and(postEntity.regDate.goe(postSearchDto.getStartDate()));
            }
            if (!StringUtils.isEmpty(postSearchDto.getEndDate())) {
                builder.and(postEntity.regDate.loe(postSearchDto.getEndDate()));
            }
        }else {
            if (!StringUtils.isEmpty(postSearchDto.getStartDate())) {
                builder.and(postEntity.updateDate.goe(postSearchDto.getStartDate()));
            }
            if (!StringUtils.isEmpty(postSearchDto.getEndDate())) {
                builder.and(postEntity.updateDate.loe(postSearchDto.getEndDate()));
            }
        }
        return builder;
    }

}