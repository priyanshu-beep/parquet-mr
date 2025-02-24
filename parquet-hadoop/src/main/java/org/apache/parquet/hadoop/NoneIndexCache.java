/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.parquet.hadoop;

import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;

import java.io.IOException;

/**
 * Cache nothing. All the get methods are pushed to ParquetFileReader to read the given index.
 */
class NoneIndexCache implements IndexCache {
  private final ParquetFileReader fileReader;

  NoneIndexCache(ParquetFileReader fileReader) {
    this.fileReader = fileReader;
  }

  @Override
  public void setBlockMetadata(BlockMetaData currentBlockMetadata) throws IOException {
    // Do nothing
  }

  @Override
  public ColumnIndex getColumnIndex(ColumnChunkMetaData chunk) throws IOException {
    return fileReader.readColumnIndex(chunk);
  }

  @Override
  public OffsetIndex getOffsetIndex(ColumnChunkMetaData chunk) throws IOException {
    return fileReader.readOffsetIndex(chunk);
  }

  @Override
  public BloomFilter getBloomFilter(ColumnChunkMetaData chunk) throws IOException {
    return fileReader.readBloomFilter(chunk);
  }

  @Override
  public void clean() {
    // Do nothing
  }
}
