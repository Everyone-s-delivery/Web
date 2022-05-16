package everyone.delivery.demo.domain.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import everyone.delivery.demo.common.request.dto.KeyColumn;
import everyone.delivery.demo.common.request.dto.OrderBy;
import everyone.delivery.demo.common.request.dto.PagingRequestDto;
import everyone.delivery.demo.common.utils.TimeUtils;
import everyone.delivery.demo.domain.post.PostEntity;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import java.util.List;

import static everyone.delivery.demo.domain.post.QPostEntity.postEntity;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostQueryDSLRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PostEntity> getPagedList(PostSearchDto postSearchDto, PagingRequestDto pagingRequestDto) {
        QueryResults<?> queryResults = jpaQueryFactory.from(postEntity)
                .where(getWhereBuilder(postSearchDto, pagingRequestDto))
                .orderBy(getOrderSpecifier(pagingRequestDto))
                .limit(pagingRequestDto.getFetchSize() + 1)
                .fetchResults();
        return (List<PostEntity>) queryResults.getResults();
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

    private BooleanBuilder getWhereBuilder(PostSearchDto postSearchDto, PagingRequestDto pagingRequestDto){
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
        KeyColumn keyColumn = pagingRequestDto.getKeyColumn();
        Long timeCursor = pagingRequestDto.getCursor();
        OrderBy orderBy = pagingRequestDto.getOrderBy();

        if(timeCursor != null){
            if(keyColumn.equals(KeyColumn.REG_DATE)){
                if(orderBy.isAsc()){
                    builder.and(postEntity.regDate.goe(TimeUtils.longToLocalDateTime(timeCursor)));
                }
                else{
                    builder.and(postEntity.regDate.loe(TimeUtils.longToLocalDateTime(timeCursor)));
                }
            }else {
                if(orderBy.isAsc()){
                    builder.and(postEntity.updateDate.goe(TimeUtils.longToLocalDateTime(timeCursor)));
                }
                else{
                    builder.and(postEntity.updateDate.loe(TimeUtils.longToLocalDateTime(timeCursor)));
                }
            }
        }
        return builder;
    }

}
