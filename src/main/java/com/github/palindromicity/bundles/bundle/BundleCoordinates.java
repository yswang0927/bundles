/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.palindromicity.bundles.bundle;

/**
 * The coordinates of a bundle (group, artifact, version).
 */
public class BundleCoordinates {

  public static final String DEFAULT_GROUP = "default";
  public static final String DEFAULT_VERSION = "unversioned";

  public static final BundleCoordinates UNKNOWN_COORDINATE = new BundleCoordinates(DEFAULT_GROUP,
      "unknown", DEFAULT_VERSION);

  private final String group;
  private final String id;
  private final String version;
  private final String coordinates;

  /**
   * Constructor.
   * @param group the Group
   * @param id the ID
   * @param version the Version
   */
  public BundleCoordinates(final String group, final String id, final String version) {
    this.group = isBlank(group) ? DEFAULT_GROUP : group;
    this.id = id;
    this.version = isBlank(version) ? DEFAULT_VERSION : version;

    if (isBlank(id)) {
      throw new IllegalStateException("Id is required for BundleCoordinates");
    }

    if (this.group.contains(":") || this.id.contains(":") || this.version.contains(":")) {
      throw new IllegalStateException(String
          .format("Invalid coordinates: cannot contain : character group[%s] id[%s] version[%s]",
              this.group, this.id, this.version));
    }
    this.coordinates = this.group + ":" + this.id + ":" + this.version;
  }

  private boolean isBlank(String str) {
    return str == null || str.trim().length() == 0;
  }

  public String getGroup() {
    return group;
  }

  public String getId() {
    return id;
  }

  public String getVersion() {
    return version;
  }

  public final String getCoordinates() {
    return coordinates;
  }

  @Override
  public String toString() {
    return coordinates;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (!(obj instanceof BundleCoordinates)) {
      return false;
    }

    final BundleCoordinates other = (BundleCoordinates) obj;
    return getCoordinates().equals(other.getCoordinates());
  }

  @Override
  public int hashCode() {
    return 37 * this.coordinates.hashCode();
  }

}
