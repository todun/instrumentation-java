/*
 * Copyright 2017, Google Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.instrumentation.trace;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Unit tests for {@link BlankSpan}. */
@RunWith(JUnit4.class)
public class BlankSpanTest {
  @Test
  public void hasInvalidContextAndDefaultSpanOptions() {
    assertThat(BlankSpan.INSTANCE.getContext()).isEqualTo(SpanContext.INVALID);
    assertThat(BlankSpan.INSTANCE.getOptions().isEmpty()).isTrue();
  }

  @Test
  public void doNotCrash() {
    // Tests only that all the methods are not crashing/throwing errors.
    BlankSpan.INSTANCE
        .addLabels(Labels.builder().putStringLabel("MyLabelKey", "MyLabelValue").build());
    BlankSpan.INSTANCE.addAnnotation("MyAnnotation");
    BlankSpan.INSTANCE
        .addAnnotation(
            "MyAnnotation", Labels.builder().putStringLabel("MyLabelKey", "MyLabelValue").build());
    BlankSpan.INSTANCE
        .addNetworkEvent(NetworkEvent.builder(NetworkEvent.Type.SENT, 1L).build());
    BlankSpan.INSTANCE.addChildLink(BlankSpan.INSTANCE);
    BlankSpan.INSTANCE.end(EndSpanOptions.DEFAULT);
  }

  @Test
  public void blankSpan_ToString() {
    assertThat(BlankSpan.INSTANCE.toString()).isEqualTo("BlankSpan");
  }
}
