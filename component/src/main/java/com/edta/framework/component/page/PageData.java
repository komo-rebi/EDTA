package com.edta.framework.component.page;

import lombok.Data;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangluyao
 * @date 2023/3/1 15:35
 * @description
 */
@Data
public abstract class PageData<T> {

    public static <T> PageData<T> results(SearchHits<T> searchHits) {
        return new NoIteratorPageData<>(searchHits);
    }

    public static <T> PageData<T> results(SearchHitsIterator<T> searchHitsIterator) {
        return new IteratorPageData<>(searchHitsIterator);
    }

    public abstract boolean hasNext();

    public abstract T next();

    public abstract Long getTotalSize();

    public static class NoIteratorPageData<T> extends PageData<T> {

        private final AtomicInteger currentCount = new AtomicInteger();
        private final SearchHits<T> searchHits;

        public NoIteratorPageData(SearchHits<T> searchHits) {
            this.searchHits = searchHits;
        }

        @Override
        public boolean hasNext() {
            return currentCount.get() < searchHits.getTotalHits();
        }

        @Override
        public T next() {
            return searchHits.getSearchHit(currentCount.getAndIncrement()).getContent();
        }

        @Override
        public Long getTotalSize() {
            return null;
        }
    }

    public static class IteratorPageData<T> extends PageData<T> {

        private final SearchHitsIterator<T> searchHitsIterator;

        public IteratorPageData(SearchHitsIterator<T> searchHitsIterator) {
            this.searchHitsIterator = searchHitsIterator;
        }

        @Override
        public boolean hasNext() {
            return searchHitsIterator.hasNext();
        }

        @Override
        public T next() {
            return searchHitsIterator.next().getContent();
        }

        @Override
        public Long getTotalSize() {
            return searchHitsIterator.getTotalHits();
        }
    }
}
