/*
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

package com.github.housepower.jdbc.settings;

public enum SettingKey {
    min_compress_block_size(ISettingType.Int64, "The actual size of the block to compress, if the uncompressed data less than max_compress_block_size is no less than this value and no less than the volume of data for one mark."),
    max_compress_block_size(ISettingType.Int64, "The maximum size of blocks of uncompressed data before compressing for writing to a table."),
    max_block_size(ISettingType.Int64, "Maximum block size for reading"),
    max_insert_block_size(ISettingType.Int64, "The maximum block size for insertion, if we control the creation of blocks for insertion."),
    min_insert_block_size_rows(ISettingType.Int64, "Squash blocks passed to INSERT query to specified size in rows, if blocks are not big enough."),
    min_insert_block_size_bytes(ISettingType.Int64, "Squash blocks passed to INSERT query to specified size in bytes, if blocks are not big enough."),
    max_read_buffer_size(ISettingType.Int64, "The maximum size of the buffer to read from the filesystem."),
    max_distributed_connections(ISettingType.Int64, "The maximum number of connections for distributed processing of one query (should be greater than max_threads)."),
    max_query_size(ISettingType.Int64, "Which part of the query can be read into RAM for parsing (the remaining data for INSERT, if any, is read later)"),
    interactive_delay(ISettingType.Int64, "The interval in microseconds to check if the request is cancelled, and to send progress info."),
    connect_timeout(ISettingType.Seconds, "Connection timeout if there are no replicas."),
    connect_timeout_with_failover_ms(ISettingType.Milliseconds, "Connection timeout for selecting first healthy replica."),
    queue_max_wait_ms(ISettingType.Milliseconds, "The wait time in the request queue, if the number of concurrent requests exceeds the maximum."),
    poll_interval(ISettingType.Int64, "Block at the query wait loop on the server for the specified number of seconds."),
    distributed_connections_pool_size(ISettingType.Int64, "Maximum number of connections with one remote server in the pool."),
    connections_with_failover_max_tries(ISettingType.Int64, "The maximum number of attempts to connect to replicas."),
    extremes(ISettingType.Boolean, "Calculate minimums and maximums of the result columns. They can be output in JSON-formats."),
    use_uncompressed_cache(ISettingType.Boolean, "Whether to use the cache of uncompressed blocks."),
    replace_running_query(ISettingType.Boolean, "Whether the running request should be canceled with the same id as the new one."),
    background_pool_size(ISettingType.Int64, "Number of threads performing background work for tables (for example, merging in merge tree). Only has meaning at server startup."),
    background_schedule_pool_size(ISettingType.Int64, "Number of threads performing background tasks for replicated tables. Only has meaning at server startup."),
    distributed_directory_monitor_sleep_time_ms(ISettingType.Milliseconds, "Sleep time for StorageDistributed DirectoryMonitors in case there is no work or exception has been thrown."),
    distributed_directory_monitor_batch_inserts(ISettingType.Boolean, "Should StorageDistributed DirectoryMonitors try to batch individual inserts into bigger ones."),
    optimize_move_to_prewhere(ISettingType.Boolean, "Allows disabling WHERE to PREWHERE optimization in SELECT queries from MergeTree."),
    replication_alter_partitions_sync(ISettingType.Int64, "Wait for actions to manipulate the partitions. 0 - do not wait, 1 - wait for execution only of itself, 2 - wait for everyone."),
    replication_alter_columns_timeout(ISettingType.Int64, "Wait for actions to change the table structure within the specified number of seconds. 0 - wait unlimited time."),
    totals_auto_threshold(ISettingType.Float, "The threshold for totals_mode = 'auto'."),
    compile(ISettingType.Boolean, "Whether query compilation is enabled."),
    compile_expressions(ISettingType.Boolean, "Compile some scalar functions and operators to native code."),
    min_count_to_compile(ISettingType.Int64, "The number of structurally identical queries before they are compiled."),
    group_by_two_level_threshold(ISettingType.Int64, "From what number of keys, a two-level aggregation starts. 0 - the threshold is not set."),
    group_by_two_level_threshold_bytes(ISettingType.Int64, "From what size of the aggregation state in bytes, a two-level aggregation begins to be used. 0 - the threshold is not set. Two-level aggregation is used when at least one of the thresholds is triggered."),
    distributed_aggregation_memory_efficient(ISettingType.Boolean, "Is the memory-saving mode of distributed aggregation enabled."),
    aggregation_memory_efficient_merge_threads(ISettingType.Int64, "Number of threads to use for merge intermediate aggregation results in memory efficient mode. When bigger, then more memory is consumed. 0 means - same as 'max_threads'."),
    max_threads(ISettingType.Int64, "The maximum number of threads to execute the request. By default, it is determined automatically."),
    max_parallel_replicas(ISettingType.Int64, "The maximum number of replicas of each shard used when the query is executed. For consistency (to get different parts of the same partition), this option only works for the specified sampling key. The lag of the replicas is not controlled."),
    skip_unavailable_shards(ISettingType.Boolean, "Silently skip unavailable shards."),
    distributed_group_by_no_merge(ISettingType.Boolean, "Do not merge aggregation states from different servers for distributed query processing - in case it is for certain that there are different keys on different shards."),
    merge_tree_min_rows_for_concurrent_read(ISettingType.Int64, "If at least as many lines are read from one file, the reading can be parallelized."),
    merge_tree_min_rows_for_seek(ISettingType.Int64, "You can skip reading more than that number of rows at the price of one seek per file."),
    merge_tree_coarse_index_granularity(ISettingType.Int64, "If the index segment can contain the required keys, divide it into as many parts and recursively check them. "),
    merge_tree_max_rows_to_use_cache(ISettingType.Int64, "The maximum number of rows per request, to use the cache of uncompressed data. If the request is large, the cache is not used. (For large queries not to flush out the cache.)"),
    merge_tree_uniform_read_distribution(ISettingType.Boolean, "Distribute read from MergeTree over threads evenly, ensuring stable average execution time of each thread within one read operation."),
    mysql_max_rows_to_insert(ISettingType.Int64, "The maximum number of rows in MySQL batch insertion of the MySQL storage engine"),
    optimize_min_equality_disjunction_chain_length(ISettingType.Int64, "The minimum length of the expression `expr = x1 OR ... expr = xN` for optimization "),
    min_bytes_to_use_direct_io(ISettingType.Int64, "The minimum number of bytes for input/output operations is bypassing the page cache. 0 - disabled."),
    force_index_by_date(ISettingType.Boolean, "Throw an exception if there is a partition key in a table, and it is not used."),
    force_primary_key(ISettingType.Boolean, "Throw an exception if there is primary key in a table, and it is not used."),
    mark_cache_min_lifetime(ISettingType.Int64, "If the maximum size of mark_cache is exceeded, delete only records older than mark_cache_min_lifetime seconds."),
    max_streams_to_max_threads_ratio(ISettingType.Float, "Allows you to use more sources than the number of threads - to more evenly distribute work across threads. It is assumed that this is a temporary solution, since it will be possible in the future to make the number of sources equal to the number of threads, but for each source to dynamically select available work for itself."),
    network_zstd_compression_level(ISettingType.Int64, "Allows you to select the level of ZSTD compression."),
    priority(ISettingType.Int64, "Priority of the query. 1 - the highest, higher value - lower priority; 0 - do not use priorities."),
    log_queries(ISettingType.Boolean, "Log requests and write the log to the system table."),
    log_queries_cut_to_length(ISettingType.Int64, "If query length is greater than specified threshold (in bytes), then cut query when writing to query log. Also limit length of printed query in ordinary text log."),
    max_concurrent_queries_for_user(ISettingType.Int64, "The maximum number of concurrent requests per user."),
    insert_deduplicate(ISettingType.Boolean, "For INSERT queries in the replicated table, specifies that deduplication of insertings blocks should be preformed"),
    insert_quorum(ISettingType.Int64, "For INSERT queries in the replicated table, wait writing for the specified number of replicas and linearize the addition of the data. 0 - disabled."),
    select_sequential_consistency(ISettingType.Int64, "For SELECT queries from the replicated table, throw an exception if the replica does not have a chunk written with the quorum; do not read the parts that have not yet been written with the quorum."),
    table_function_remote_max_addresses(ISettingType.Int64, "The maximum number of different shards and the maximum number of replicas of one shard in the `remote` function."),
    read_backoff_min_latency_ms(ISettingType.Milliseconds, "Setting to reduce the number of threads in case of slow reads. Pay attention only to reads that took at least that much time."),
    read_backoff_max_throughput(ISettingType.Int64, "Settings to reduce the number of threads in case of slow reads. Count events when the read bandwidth is less than that many bytes per second."),
    read_backoff_min_interval_between_events_ms(ISettingType.Milliseconds, "Settings to reduce the number of threads in case of slow reads. Do not pay attention to the event, if the previous one has passed less than a certain amount of time."),
    read_backoff_min_events(ISettingType.Int64, "Settings to reduce the number of threads in case of slow reads. The number of events after which the number of threads will be reduced."),
    memory_tracker_fault_probability(ISettingType.Float, "For testing of `exception safety` - throw an exception every time you allocate memory with the specified probability."),
    enable_http_compression(ISettingType.Boolean, "Compress the result if the client over HTTP said that it understands data compressed by gzip or deflate."),
    http_zlib_compression_level(ISettingType.Int64, "Compression level - used if the client on HTTP said that it understands data compressed by gzip or deflate."),
    http_native_compression_disable_checksumming_on_decompress(ISettingType.Boolean, "If you uncompress the POST data from the client compressed by the native format, do not check the checksum."),
    count_distinct_implementation(ISettingType.String, "What aggregate function to use for implementation of count(DISTINCT ...)"),
    output_format_write_statistics(ISettingType.Boolean, "Write statistics about read rows, bytes, time elapsed in suitable output formats."),
    add_http_cors_header(ISettingType.Boolean, "Write add http CORS header."),
    input_format_skip_unknown_fields(ISettingType.Boolean, "Skip columns with unknown names from input data (it works for JSONEachRow and TSKV formats)."),
    input_format_values_interpret_expressions(ISettingType.Boolean, "For Values format: if field could not be parsed by streaming parser, run SQL parser and try to interpret it as SQL expression."),
    output_format_json_quote_64bit_integers(ISettingType.Boolean, "Controls quoting of 64-bit integers in JSON output format."),
    output_format_json_quote_denormals(ISettingType.Boolean, "Enables '+nan', '-nan', '+inf', '-inf' outputs in JSON output format."),
    output_format_pretty_max_rows(ISettingType.Int64, "Rows limit for Pretty formats."),
    use_client_time_zone(ISettingType.Boolean, "Use client timezone for interpreting DateTime string values, instead of adopting server timezone."),
    send_progress_in_http_headers(ISettingType.Boolean, "Send progress notifications using X-ClickHouse-Progress headers. Some clients do not support high amount of HTTP headers (Python requests in particular), so it is disabled by default."),
    http_headers_progress_interval_ms(ISettingType.Int64, "Do not send HTTP headers X-ClickHouse-Progress more frequently than at each specified interval."),
    fsync_metadata(ISettingType.Boolean, "Do fsync after changing metadata for tables and databases (.sql files). Could be disabled in case of poor latency on server with high load of DDL queries and high load of disk subsystem."),
    input_format_allow_errors_num(ISettingType.Int64, "Maximum absolute amount of errors while reading text formats (like CSV, TSV). In case of error, if both absolute and relative values are non-zero, and at least absolute or relative amount of errors is lower than corresponding value, will skip until next line and continue."),
    input_format_allow_errors_ratio(ISettingType.Float, "Maximum relative amount of errors while reading text formats (like CSV, TSV). In case of error, if both absolute and relative values are non-zero, and at least absolute or relative amount of errors is lower than corresponding value, will skip until next line and continue."),
    join_use_nulls(ISettingType.Boolean, "Use NULLs for non-joined rows of outer JOINs. If false, use default value of corresponding columns data type."),
    max_replica_delay_for_distributed_queries(ISettingType.Int64, "If set, distributed queries of Replicated tables will choose servers with replication delay in seconds less than the specified value (not inclusive). Zero means do not take delay into account."),
    fallback_to_stale_replicas_for_distributed_queries(ISettingType.Boolean, "Suppose max_replica_delay_for_distributed_queries is set and all replicas for the queried table are stale. If this setting is enabled, the query will be performed anyway, otherwise the error will be reported."),
    preferred_max_column_in_block_size_bytes(ISettingType.Int64, "Limit on max column size in block while reading. Helps to decrease cache misses count. Should be close to L2 cache size."),
    insert_distributed_sync(ISettingType.Boolean, "If setting is enabled, insert query into distributed waits until data will be sent to all nodes in cluster."),
    insert_distributed_timeout(ISettingType.Int64, "Timeout for insert query into distributed. Setting is used only with insert_distributed_sync enabled. Zero value means no timeout."),
    distributed_ddl_task_timeout(ISettingType.Int64, "Timeout for DDL query responses from all hosts in cluster. Negative value means infinite."),
    stream_flush_interval_ms(ISettingType.Milliseconds, "Timeout for flushing data from streaming storages."),
    format_schema(ISettingType.String, "Schema identifier (used by schema-based formats)"),
    insert_allow_materialized_columns(ISettingType.Boolean, "If setting is enabled, Allow materialized columns in INSERT."),
    http_connection_timeout(ISettingType.Seconds, "HTTP connection timeout."),
    http_send_timeout(ISettingType.Seconds, "HTTP send timeout"),
    http_receive_timeout(ISettingType.Seconds, "HTTP receive timeout"),
    optimize_throw_if_noop(ISettingType.Boolean, "If setting is enabled and OPTIMIZE query didn't actually assign a merge then an explanatory exception is thrown"),
    use_index_for_in_with_subqueries(ISettingType.Boolean, "Try using an index if there is a subquery or a table expression on the right side of the IN operator."),
    empty_result_for_aggregation_by_empty_set(ISettingType.Boolean, "Return empty result when aggregating without keys on empty set."),
    allow_distributed_ddl(ISettingType.Boolean, "If it is set to true, then a user is allowed to executed distributed DDL queries."),
    odbc_max_field_size(ISettingType.Int64, "Max size of filed can be read from ODBC dictionary. Long strings are truncated."),
    max_rows_to_read(ISettingType.Int64, "Limit on read rows from the most 'deep' sources. That is, only in the deepest subquery. When reading from a remote server, it is only checked on a remote server."),
    max_bytes_to_read(ISettingType.Int64, "Limit on read bytes (after decompression) from the most 'deep' sources. That is, only in the deepest subquery. When reading from a remote server, it is only checked on a remote server."),
    max_result_rows(ISettingType.Int64, "Limit on result size in rows. Also checked for intermediate data sent from remote servers."),
    max_result_bytes(ISettingType.Int64, "Limit on result size in bytes (uncompressed). Also checked for intermediate data sent from remote servers."),
    min_execution_speed(ISettingType.Int64, "In rows per second."),
    timeout_before_checking_execution_speed(ISettingType.Seconds, "Check that the speed is not too low after the specified time has elapsed."),
    max_ast_depth(ISettingType.Int64, "Maximum depth of query syntax tree. Checked after parsing."),
    max_ast_elements(ISettingType.Int64, "Maximum size of query syntax tree in number of nodes. Checked after parsing."),
    max_expanded_ast_elements(ISettingType.Int64, "Maximum size of query syntax tree in number of nodes after expansion of aliases and the asterisk."),
    readonly(ISettingType.Int64, "0 - everything is allowed. 1 - only read requests. 2 - only read requests, as well as changing settings, except for the 'readonly' setting."),
    max_rows_in_set(ISettingType.Int64, "Maximum size of the set (in number of elements) resulting from the execution of the IN section."),
    max_bytes_in_set(ISettingType.Int64, "Maximum size of the set (in bytes in memory) resulting from the execution of the IN section."),
    max_rows_in_join(ISettingType.Int64, "Maximum size of the hash table for JOIN (in number of rows)."),
    max_bytes_in_join(ISettingType.Int64, "Maximum size of the hash table for JOIN (in number of bytes in memory)."),
    max_rows_to_transfer(ISettingType.Int64, "Maximum size (in rows) of the transmitted external table obtained when the GLOBAL IN/JOIN section is executed."),
    max_bytes_to_transfer(ISettingType.Int64, "Maximum size (in uncompressed bytes) of the transmitted external table obtained when the GLOBAL IN/JOIN section is executed."),
    max_rows_in_distinct(ISettingType.Int64, "Maximum number of elements during execution of DISTINCT."),
    max_bytes_in_distinct(ISettingType.Int64, "Maximum total size of state (in uncompressed bytes) in memory for the execution of DISTINCT."),
    max_memory_usage(ISettingType.Int64, "Maximum memory usage for processing of single query. Zero means unlimited."),
    max_memory_usage_for_user(ISettingType.Int64, "Maximum memory usage for processing all concurrently running queries for the user. Zero means unlimited."),
    max_memory_usage_for_all_queries(ISettingType.Int64, "Maximum memory usage for processing all concurrently running queries on the server. Zero means unlimited."),
    max_network_bandwidth(ISettingType.Int64, "The maximum speed of data exchange over the network in bytes per second for a query. Zero means unlimited."),
    max_network_bytes(ISettingType.Int64, "The maximum number of bytes (compressed) to receive or transmit over the network for execution of the query."),
    max_network_bandwidth_for_user(ISettingType.Int64, "The maximum speed of data exchange over the network in bytes per second for all concurrently running user queries. Zero means unlimited."),
    max_network_bandwidth_for_all_users(ISettingType.Int64, "The maximum speed of data exchange over the network in bytes per second for all concurrently running queries. Zero means unlimited."),
    format_csv_delimiter(ISettingType.Character, "The character to be considered as a delimiter in CSV data. If setting with a string, a string has to have a length of 1."),
    enable_conditional_computation(ISettingType.Int64, "Enable conditional computations"),
    port(ISettingType.Int32, ""),
    user(ISettingType.String, ""),
    address(ISettingType.String, ""),
    database(ISettingType.String, ""),
    password(ISettingType.String, ""),
    query_timeout(ISettingType.Seconds, "");

    private final String describe;
    private final ISettingType type;

    SettingKey(ISettingType type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public String describe() {
        return describe;
    }

    public ISettingType type() {
        return type;
    }
}
