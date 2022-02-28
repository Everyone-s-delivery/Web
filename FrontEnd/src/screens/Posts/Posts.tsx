import { useDispatch } from 'react-redux';

import { getListingAction } from 'src/redux/listing/listingActions';
import { ScrollPageWrapper } from '../../components/@styled/layout';
import { QUERY } from '../../constants/queries';
import { Container, ContentWrapper } from './Posts.style';

const Posts = () => {
  const dispatch = useDispatch();
  dispatch(getListingAction());

  return (
    <ScrollPageWrapper>
      <Container>
        <ContentWrapper>
          {/* <PostList
            users={results}
            isFetchingNextPage={isFetchingNextPage}
            onIntersect={handleIntersect}
            queryKey={[QUERY.GET_POSTS, { username }]}
          /> */}
        </ContentWrapper>
      </Container>
    </ScrollPageWrapper>
  );
};

export default Posts;
