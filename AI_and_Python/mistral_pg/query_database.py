from langchain_community.document_loaders import DirectoryLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter
from sentence_transformers import SentenceTransformer
import numpy as np
from langchain.schema import Document
from langchain_community.vectorstores import PGVector
from pgvector.psycopg2 import register_vector
import psycopg2
import uuid
import json
from credentials import DBNAME, USER, PASSWORD, HOST, PORT
import nltk
# nltk.download('punkt_tab')
# nltk.download('averaged_perceptron_tagger_eng')
from langchain_community.llms import Ollama
import argparse

def perform_similarity_search(dbname, user, password, host, port, query_embedding, top_k=5, collection_id=None):
    if collection_id is None:
        # Establish a connection to the PostgreSQL database
        conn = psycopg2.connect(
            dbname=dbname,
            user=user,
            password=password,
            host=host,
            port=port
        )
        
        # Register the vector type for this connection
        register_vector(conn)
        
        # Create a new cursor
        cur = conn.cursor()
        cur.execute('CREATE EXTENSION IF NOT EXISTS vector')

        # Prepare the SQL query for performing the similarity search
        # Assuming `embedding` column is stored as a vector type compatible with pgvector
        similarity_search_query = """
        SELECT document, (embedding <-> %(query_embedding)s) AS distance
        FROM rslt_embeddings
        ORDER BY distance
        LIMIT %(top_k)s;
        """
        
        # Execute the query
        cur.execute(similarity_search_query, {'query_embedding': query_embedding, 'top_k': top_k})
        
        # Fetch the results
        results = cur.fetchall()
        
        # Close the cursor and connection
        cur.close()
        conn.close()
        return results
        
    else:
        # Establish a connection to the PostgreSQL database
        conn = psycopg2.connect(
            dbname=dbname,
            user=user,
            password=password,
            host=host,
            port=port
        )
        
        # Register the vector type for this connection
        register_vector(conn)
        
        # Create a new cursor
        cur = conn.cursor()
        cur.execute('CREATE EXTENSION IF NOT EXISTS vector')

        # Prepare the SQL query for performing the similarity search
        # Assuming `embedding` column is stored as a vector type compatible with pgvector
        similarity_search_query = """
        SELECT document, (embedding <-> %(query_embedding)s) AS distance
        FROM rslt_embeddings
        WHERE collection_id = %(collection_id)s
        ORDER BY distance
        LIMIT %(top_k)s;
        """
        
        # Execute the query
        cur.execute(similarity_search_query, {'query_embedding': query_embedding, 'top_k': top_k, 'collection_id': collection_id})
        
        # Fetch the results
        results = cur.fetchall()
        
        # Close the cursor and connection
        cur.close()
        conn.close()
        return results
  

def encode_query(text):
    model = SentenceTransformer("all-minilm-l6-v2")
    query_embedding = model.encode(text)
    return query_embedding


def main():
    # Create CLI.
    parser = argparse.ArgumentParser()
    parser.add_argument("query_text", type=str, help="The query text.")
    args = parser.parse_args()
    query_text = args.query_text
    
    llm = Ollama(model="mistral:7b")
    query_embedding = encode_query(query_text)
    results = perform_similarity_search(DBNAME, USER, PASSWORD, HOST, PORT, query_embedding, top_k=4)

    template = f"""Use the following pieces of context to answer the question at the end.
    If you don't find the answer in the context, just say that you don't know, don't try to make up an answer.
    Always say "thanks for asking!" at the end of the answer.

    Question: {query_text}

    Here is the context:
    """
    for i, (document, distance) in enumerate(results):
        template += f"Context {i+1}: {document} (distance: {distance: .4f})\n"

    template += "\nAnswer:"

    response = llm.invoke(template)
    print("===== Input =====")
    print(query_text)
    print()
    print("===== Output =====")
    print(response)

if __name__ == "__main__":
    main()