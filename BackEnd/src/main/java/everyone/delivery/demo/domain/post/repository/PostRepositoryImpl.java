package everyone.delivery.demo.domain.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import everyone.delivery.demo.common.request.dto.KeyColumn;
import everyone.delivery.demo.common.request.dto.OrderBy;
import everyone.delivery.demo.common.request.dto.PagingRequestDto;
import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;
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
    public Slice<PostEntity> getPagedList(PostSearchDto postSearchDto, PagingRequestDto pagingRequestDto) {
        QueryResults<?> queryResults = jpaQueryFactory.from(postEntity)
                .where(getWhereBuilder(postSearchDto, pagingRequestDto.getKeyColumn()))
                .orderBy(getOrderSpecifier(pagingRequestDto))
                .offset(pagingRequestDto.getOffset())
                .limit(pagingRequestDto.getLimit() + 1)
                .fetchResults();
        return new SliceImpl(queryResults.getResults(),PageRequest.of(pagingRequestDto.getOffset(),pagingRequestDto.getLimit()),true);
    }

    private OrderSpecifier getOrderSpecifier(PagingRequestDto pagingRequestDto){
        KeyColumn keyColumn = pagingRequestDto.getKeyColumn();
        OrderBy orderBy = pagingRequestDto.getOrderBy();

        if(keyColumn.equals(KeyColumn.REG_DATE)){
            if(orderBy.isAsc())
                return postEntity.regDate.asc();
            else
                return postEntity.regDate.desc();
        }
        else {
            if(orderBy.isAsc())
                return postEntity.updateDate.asc();
            else
                return postEntity.updateDate.desc();
        }
    }

    private BooleanBuilder getWhereBuilder(PostSearchDto postSearchDto, KeyColumn keyColumn){
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

        if(keyColumn.equals(KeyColumn.REG_DATE)){
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
