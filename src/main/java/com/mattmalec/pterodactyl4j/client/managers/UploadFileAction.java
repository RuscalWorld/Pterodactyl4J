/*
 *    Copyright 2021-2022 Matt Malec, and the Pterodactyl4J contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.mattmalec.pterodactyl4j.client.managers;

import com.mattmalec.pterodactyl4j.PteroAction;
import com.mattmalec.pterodactyl4j.utils.Checks;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public interface UploadFileAction extends PteroAction<Void> {

	UploadFileAction addFile(InputStream data, String name);

	default UploadFileAction addFile(byte[] data, String name) {
		Checks.notNull(data, "Data");
		return addFile(new ByteArrayInputStream(data), name);
	}

	default UploadFileAction addFile(final File file) {
		Checks.notNull(file, "File");
		return addFile(file, file.getName());
	}

	UploadFileAction addFile(File file, String name);

	UploadFileAction clearFiles();
}
