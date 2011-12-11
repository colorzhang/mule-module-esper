/**
 * Mule Development Kit
 * Copyright 2010-2011 (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.module.esper;

import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.FunctionalTestCase;

import org.junit.Test;

public class EsperModuleTest extends FunctionalTestCase {
    @Override
    protected String getConfigResources() {
        return "mule-config.xml";
    }

    @Test
    public void testCanInsertAndListenForEvent() throws Exception {

        MuleClient client = new MuleClient(muleContext);
        DummyEvent dummyEvent = new DummyEvent();
        client.dispatch("vm://in", dummyEvent, null);

        MuleMessage response = client.request("vm://out", 15000);
        assertNotNull(response);
        assertEquals("The output payload does not match the input payload", dummyEvent, response.getPayload());

        MuleMessage event = client.request("vm://events", 15000);
        assertNotNull(event);
        assertEquals(1L, event.getPayload());
    }

}
