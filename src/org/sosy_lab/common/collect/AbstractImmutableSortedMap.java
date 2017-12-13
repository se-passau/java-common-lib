/*
 *  SoSy-Lab Common is a library of useful utilities.
 *  This file is part of SoSy-Lab Common.
 *
 *  Copyright (C) 2007-2017  Dirk Beyer
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.sosy_lab.common.collect;

import java.util.NavigableSet;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

abstract class AbstractImmutableSortedMap<K, V> extends AbstractImmutableMap<K, V>
    implements OurSortedMap<K, V> {

  private @Nullable K keyOrNull(@Nullable Entry<K, V> entry) {
    return entry == null ? null : entry.getKey();
  }

  private K keyOrNSE(@Nullable Entry<K, V> entry) {
    if (entry == null) {
      throw new NoSuchElementException();
    }
    return entry.getKey();
  }

  @Override
  public final K firstKey() {
    return keyOrNSE(firstEntry());
  }

  @Override
  public final K lastKey() {
    return keyOrNSE(lastEntry());
  }

  @Override
  public final K ceilingKey(K pKey) {
    return keyOrNull(ceilingEntry(pKey));
  }

  @Override
  public final K floorKey(K pKey) {
    return keyOrNull(floorEntry(pKey));
  }

  @Override
  public final K higherKey(K pKey) {
    return keyOrNull(higherEntry(pKey));
  }

  @Override
  public final K lowerKey(K pKey) {
    return keyOrNull(lowerEntry(pKey));
  }

  @Override
  public final OurSortedMap<K, V> headMap(K pToKey) {
    return headMap(pToKey, /*pInclusive=*/ false);
  }

  @Override
  public final OurSortedMap<K, V> tailMap(K pFromKey) {
    return tailMap(pFromKey, /*pInclusive=*/ true);
  }

  @Override
  public final OurSortedMap<K, V> subMap(K pFromKey, K pToKey) {
    return subMap(pFromKey, /*pFromInclusive=*/ true, pToKey, /*pToInclusive=*/ false);
  }

  @Override
  public final NavigableSet<K> keySet() {
    return navigableKeySet();
  }

  @Override
  public NavigableSet<K> navigableKeySet() {
    return new SortedMapKeySet<>(this);
  }

  @Override
  public final NavigableSet<K> descendingKeySet() {
    return descendingMap().navigableKeySet();
  }

  @Override
  public final Entry<K, V> pollFirstEntry() {
    throw new UnsupportedOperationException();
  }

  @Override
  public final Entry<K, V> pollLastEntry() {
    throw new UnsupportedOperationException();
  }
}
