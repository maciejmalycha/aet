/**
 * AET
 *
 * Copyright (C) 2013 Cognifide Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cognifide.aet.communication.api.metadata;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.Pattern;
import org.apache.commons.lang3.StringUtils;

public abstract class StepResult implements Serializable {

  private static final long serialVersionUID = 7758484589529051815L;

  @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "Invalid objectID")
  private final String artifactId;

  private final List<Point> elementsPoints;
  private final List<Dimension> elementsDimensions;

  private final String pageSource;

  private List<String> errors = new ArrayList<>();

  private Map<String, String> data = new HashMap<>();

  public StepResult(String artifactId, List<Point> elementsPoints, List<Dimension> elementsDimensions, String pageSource) {
    this.artifactId = artifactId;
    this.elementsPoints = elementsPoints;
    this.elementsDimensions = elementsDimensions;
    this.pageSource = pageSource;
  }

  public StepResult(String artifactId, List<Point> elementsPoints, List<Dimension> elementsDimensions) {
    this(artifactId, elementsPoints, elementsDimensions, null);
  }

  public StepResult(String artifactId) {
    this(artifactId, null, null);
  }

  public String getArtifactId() {
    return artifactId;
  }

  public abstract String getStatusName();

  public Map<String, String> getData() {
    return ImmutableMap.copyOf(data);
  }

  public List<Point> getElementsPoints() {
    return elementsPoints;
  }

  public List<Dimension> getElementsDimensions() {
    return elementsDimensions;
  }

  public String getPageSource() {
    return pageSource;
  }

  public String addData(String key, String value) {
    return data.put(key, value);
  }

  public void addError(String error) {
    if (StringUtils.isNotBlank(error)) {
      errors.add(error);
    }
  }

  public List<String> getErrors() {
    return errors != null ? ImmutableList.copyOf(errors) : Collections.<String>emptyList();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("status", getStatusName())
        .add("artifactId", getArtifactId())
        .add("errors", getErrors())
        .add("data", getData())
        .add("elementsPoints", getElementsPoints())
        .add("elementsDimensions", getElementsDimensions())
        .toString();
  }
}
