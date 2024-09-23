import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import { resolve } from 'path';
import { viteStaticCopy } from 'vite-plugin-static-copy';

// https://vitejs.dev/config/
export default defineConfig({
  build: {
    rollupOptions: {
      input: {
        background: resolve(__dirname, 'src/chorme-extension/background.ts'),
        popup: resolve(__dirname, './index.html'),
      },
      output: {
        entryFileNames: '[name].js',
      },
    },
    outDir: 'dist',
  },
  plugins: [
    react(),
    viteStaticCopy({
      targets: [{ src: './src/chorme-extension/manifest.json', dest: '.' }],
    }),
  ],
});
